package io.github.samleticias.domain.repositorio;

import io.github.samleticias.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {
    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome( @Param("name") String name );

    @Query(" delete from Cliente c where c.name =:name ")
    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);
}

