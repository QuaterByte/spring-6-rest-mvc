package guru.springframework.spring6restmvc.controllers;

import com.sun.net.httpserver.Headers;
import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerservice;

    @RequestMapping(method = RequestMethod.GET)
   public List<Beer> beerList() {
       return  beerservice.beerList();
   }

    @RequestMapping("/{beerID}")
    public Beer getBeerById(@PathVariable("beerID") UUID id) {

        return  beerservice.getBeerById(id);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer) {
        Beer saved = beerservice.saveBeer(beer);
        Headers headers = new Headers();
        headers.add("Location", "/api/v1/beer/" + saved.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateById(@PathVariable("beerId") UUID id, @RequestBody Beer beer) {

        beerservice.updateBeerById(id, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID id) {

        beerservice.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
