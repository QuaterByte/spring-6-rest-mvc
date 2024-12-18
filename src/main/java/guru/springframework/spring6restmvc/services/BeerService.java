package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);

    List<Beer> beerList();

    Beer saveBeer(Beer beer);

    void updateBeerById(UUID id, Beer beer);

    void deleteById(UUID id);
}
