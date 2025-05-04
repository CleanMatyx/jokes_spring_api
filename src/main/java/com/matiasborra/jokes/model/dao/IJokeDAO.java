package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Joke;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * DAO para la entidad Joke.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos,
 * incluyendo consultas personalizadas con gráficos de entidad.
 *
 * @author Matias Borra
 */
public interface IJokeDAO extends JpaRepository<Joke, Long> {

    /**
     * Obtiene todas las entidades Joke con sus relaciones cargadas.
     *
     * @return Lista de entidades Joke
     */
    @Override
    @EntityGraph(attributePaths = {
            "category",
            "type",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    List<Joke> findAll();

    /**
     * Busca una entidad Joke por su ID con sus relaciones cargadas.
     *
     * @param id ID de la entidad Joke
     * @return Entidad Joke envuelta en un Optional
     */
    @Override
    @EntityGraph(attributePaths = {
            "category",
            "type",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    Optional<Joke> findById(Long id);

    /**
     * Obtiene todas las entidades Joke ordenadas por ID de forma ascendente,
     * con sus relaciones cargadas.
     *
     * @return Lista de entidades Joke ordenadas por ID
     */
    @Query("select j from Joke j " +
            "left join fetch j.category " +
            "left join fetch j.type " +
            "left join fetch j.language " +
            "left join fetch j.jokeFlags jf " +
            "left join fetch jf.flag " +
            "left join fetch j.primeraVez pv " +
            "left join fetch pv.telefonos " +
            "order by j.id")
    List<Joke> findAllByOrderByIdAscWithNulls();
    List<Joke> findAllByOrderByIdAsc();

    /**
     * Obtiene todas las entidades Joke con sus relaciones PrimeraVez y Telefonos cargadas,
     * ordenadas por ID.
     *
     * @return Lista de entidades Joke con relaciones cargadas
     */
    @Query("select j from Joke j join fetch j.primeraVez pv join fetch pv.telefonos t order by j.id")
    List<Joke> findAllWithPrimeraVezAndTelefonos();

    /**
     * Busca entidades Joke cuyo texto contenga una cadena específica,
     * ignorando mayúsculas y minúsculas.
     *
     * @param text Texto a buscar
     * @return Lista de entidades Joke que coinciden con el texto
     */
    List<Joke> findByText1ContainingIgnoreCase(String text);

    List<Joke> findByPrimeraVezIsNullOrderByIdAsc();

    /**
     * Recupera id, text1 y idioma de todos los chistes que tengan el flag dada
     */
    List<FlagJokeProjection> findByFlags_Id(Long flagId);

}