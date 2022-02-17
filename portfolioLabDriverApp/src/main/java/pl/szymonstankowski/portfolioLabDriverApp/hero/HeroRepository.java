package pl.szymonstankowski.portfolioLabDriverApp.hero;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Override
    void deleteById(Long id);
}
