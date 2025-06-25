package br.com.alura.LiterAlura.Principal;

import br.com.alura.LiterAlura.model.*;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoAPI;
import br.com.alura.LiterAlura.service.ConverteDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final Logger log = LoggerFactory.getLogger(Principal.class);
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {

        var opcao = -1;
        while (opcao != 0) {
            String menuTexto = """
            
            +------------------------------------+
            |        ESCOLHA A SUA OPÇÃO         |
            +------------------------------------+
            | 1 - Buscar livro pelo título       |
            | 2 - Listar livros registrados      |
            | 3 - Listar autores registrados     |
            | 4 - Listar autores vivos em um ano |
            | 5 - Listar livros por idioma       |
            | 0 - Sair                           |
            +------------------------------------+
            
            """; // Usando nome 'menuTexto' para evitar conflito com 'menu()'
            System.out.println(menuTexto);

            try {
                opcao = sc.nextInt();
                sc.nextLine(); // Consome o '\n' restante após nextInt()

                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo do LiterAlura. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, digite um número entre 0 e 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
                sc.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
                opcao = -1; // Garante que o loop continue
            }
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês 
                pt - português
                """);
        var idiomaBusca = sc.nextLine();
        List<Livro> livrosEncontrados = livroRepository.findByIdioma(idiomaBusca);
        if (!livrosEncontrados.isEmpty()){
            System.out.println("\n--- Livros encontrados no idioma '" + idiomaBusca + "' ---");
            livrosEncontrados.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado no idioma '" + idiomaBusca + "'.");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Busque autores vivos no ano de? ");
        var anoBusca = sc.nextInt();
        Optional<List<Autor>> autoresVivo = autorRepository.listarAutoresVivos(anoBusca);
        if (autoresVivo.isPresent()){
            System.out.println("\nAutores vivos: ");
            autoresVivo.get().forEach(System.out::println);
        } else {
            System.out.println("Não foi encontrado no banco nenhum autor vivo em " + anoBusca);
        }
    }

    private void listarLivrosRegistrados() {
        var livros = livroRepository.findAll();
        livros.forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {
        var autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = sc.nextLine();

        Optional<Livro> livroExistenteNoBd = livroRepository.findByTituloContainingIgnoreCase(nomeLivro);

        if (livroExistenteNoBd.isPresent()) {
            System.out.println("Livro já cadastrado no banco de dados:");
            System.out.println(livroExistenteNoBd.get());
        } else {

            String enderecoBusca = ENDERECO + nomeLivro.replace(" ", "%20").trim();
            String jsonResposta = consumoAPI.obterDados(enderecoBusca);


            if (jsonResposta == null || jsonResposta.isEmpty() || jsonResposta.equals("[]") || jsonResposta.equals("{}")) {
                System.out.println("Nenhum resultado encontrado na API para o livro: " + nomeLivro);
                return; // Encerra o método se não houver dados
            }


            DadosLivros dadosCompletos = converteDados.obterDados(jsonResposta, DadosLivros.class);


            Optional<DadosLivro> dadosPrimeiroLivroOptional = dadosCompletos.livros().stream().findFirst();

            if (dadosPrimeiroLivroOptional.isPresent()) {
                DadosLivro dadosPrimeiroLivro = dadosPrimeiroLivroOptional.get();


                Optional<DadosAutor> dadosPrimeiroAutorOptional = dadosPrimeiroLivro.autor().stream().findFirst(); // Assumindo 'autor()' retorna uma List<DadosAutor>

                if (dadosPrimeiroAutorOptional.isPresent()) {
                    DadosAutor dadosPrimeiroAutor = dadosPrimeiroAutorOptional.get();


                    Optional<Autor> autorExistenteNoBd = autorRepository.findBynome(dadosPrimeiroAutor.nome());

                    Autor autorParaSalvar;
                    if (autorExistenteNoBd.isPresent()) {
                        autorParaSalvar = autorExistenteNoBd.get();
                        System.out.println("Autor já cadastrado: " + autorParaSalvar.getNome());
                    } else {
                        autorParaSalvar = new Autor(dadosPrimeiroAutor); // Criar novo Autor se não existir
                        autorRepository.save(autorParaSalvar); // Salvar o novo autor
                        System.out.println("Novo autor cadastrado: " + autorParaSalvar.getNome());
                    }

                    Livro novoLivro = new Livro(dadosPrimeiroLivro, autorParaSalvar);
                    livroRepository.save(novoLivro);
                    System.out.println("Livro encontrado e cadastrado:");
                    System.out.println(novoLivro);

                } else {
                    System.out.println("O primeiro livro retornado pela API não possui informações de autor.");
                }
            } else {
                System.out.println("A busca na API não retornou livros válidos para o título: " + nomeLivro);
            }
        }
    }
}