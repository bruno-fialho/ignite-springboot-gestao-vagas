package com.ignite.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import com.ignite.gestao_vagas.exceptions.UserFoundException;
import com.ignite.gestao_vagas.modules.company.entities.CompanyEntity;
import com.ignite.gestao_vagas.modules.company.repositories.CompanyRepository;

public class CreateCompanyUseCase {
  
  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
      .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent(user -> {
        throw new UserFoundException();
      });

    return this.companyRepository.save(companyEntity);
  }
}
