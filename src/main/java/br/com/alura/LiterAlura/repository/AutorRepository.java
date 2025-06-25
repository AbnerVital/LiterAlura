package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findBynome(String autor);

    @Query("SELECT a FROM Autor a WHERE a.dtNascimento <= :anoBusca AND a.dtFalecimento IS NULL OR a.dtFalecimento > :anoBusca")
    Optional<List<Autor>> listarAutoresVivos(int anoBusca);
}
