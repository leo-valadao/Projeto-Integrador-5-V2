package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.filters.ClienteFiltro;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  @Query("SELECT c FROM Cliente c WHERE c.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Cliente> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

  @Query("SELECT c FROM Cliente c " +
      " INNER JOIN FETCH c.pessoa p " +
      " WHERE (:#{#filtro.id} IS NOT NULL ) AND " +
      " (:#{#filtro.id} = 0 OR :#{#filtro.id} = c.id) AND " +
      " (:#{#filtro.pessoa.nome} = '' OR p.nome LIKE CONCAT('%', :#{#filtro.pessoa.nome}, '%')) AND " +
      " (:#{#filtro.pessoa.telefone} = '' OR p.telefone LIKE CONCAT('%', :#{#filtro.pessoa.telefone}, '%')) AND " +
      " (:#{#filtro.pessoa.email} = '' OR p.email LIKE CONCAT('%', :#{#filtro.pessoa.email}, '%')) AND " +
      " (:#{#filtro.pessoa.estadoBrasileiro} IS NULL OR (:#{#filtro.pessoa.estadoBrasileiro} IS NOT NULL AND p.estadoBrasileiro IN :#{#filtro.pessoa.estadoBrasileiro})) AND "
      +
      " (:#{#filtro.pessoa.tipoPessoa} IS NULL OR (:#{#filtro.pessoa.tipoPessoa} IS NOT NULL AND p.tipoPessoa IN :#{#filtro.pessoa.tipoPessoa})) AND "
      +
      " (:#{#filtro.pessoa.cpfOuCnpj} = '' OR p.cpfOuCnpj LIKE CONCAT('%', :#{#filtro.pessoa.cpfOuCnpj}, '%')) AND " +
      " (:#{#filtro.alergias} = '' OR c.alergias LIKE CONCAT('%', :#{#filtro.alergias}, '%')) ")
  Page<Cliente> obterPorFiltroComPaginacao(@Param("filtro") ClienteFiltro filtro, Pageable pagina);

}
