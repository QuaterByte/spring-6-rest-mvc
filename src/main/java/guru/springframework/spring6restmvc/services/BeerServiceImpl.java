package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;


    public BeerServiceImpl() {
        beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .version(1)
                .quantityOnHand(120)
                .beerStyle(BeerStyle.PALE_ALE)
                .price(new BigDecimal("12.45"))
                .upc("123456")
                .id(UUID.randomUUID())
                .beername("Dog Ale")
                .updatedOn(LocalDateTime.now())
                .createdOn(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerStyle(BeerStyle.LAGER)
                .upc("654321")
                .beername("Gulder")
                .quantityOnHand(144)
                .price(new BigDecimal("12.33"))
                .updatedOn(LocalDateTime.now())
                .createdOn(LocalDateTime.now())
                .build();
        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .beerStyle(BeerStyle.GOSE)
                .upc("561234")
                .quantityOnHand(96)
                .price(new BigDecimal("11.99"))
                .version(1)
                .beername("Guinness")
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }


    @Override
    public Beer getBeerById(UUID id) {
        return  beerMap.get(id);
    }

    @Override
    public List<Beer> beerList()
    {
        return  beerMap.values().stream().toList();
    }

    @Override
    public Beer saveBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .beername(beer.getBeername())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .version(beer.getVersion())
                .build();
        beerMap.put(savedBeer.getId(), savedBeer);

        return  savedBeer;
    }

    @Override
    public void updateBeerById(UUID id, Beer beer) {
        Beer existingBeer = beerMap.get(id);
        existingBeer.setUpdatedOn(LocalDateTime.now());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setBeername(beer.getBeername());

        beerMap.put(existingBeer.getId(), existingBeer);
    }

    @Override
    public void deleteById(UUID id) {
        beerMap.remove(id);
    }
}
