package com.rushabh.ecsdemo.service;

import com.rushabh.ecsdemo.entity.Car;
import com.rushabh.ecsdemo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    @Value("${matching.word.url}")
    private String matchingWordURL;

    @Autowired
    private RestTemplate template;


    public Car addCar(Car customer) {
        return repository.save(customer);
    }

    public Car updateCar(int id, Car customer){
        if(getCar(id).isPresent()) {
            customer.setId(id);
            return repository.save(customer);
        }
        else
            return null;
    }

    public List<Car> getCars() {
        List<Car> lst = repository.findAll();
        if (lst.isEmpty())
            return new ArrayList<>();
        return lst;
    }

    public Optional<Car> getCar(int id){
        Optional<Car> optional = repository.findById(id);
        if (optional.isPresent())
            return optional;
        return Optional.empty();
    }

    public Optional<Car> getCarWithMatchingWords(int id){
        Optional<Car> optional = repository.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            car.setMatchingWords(getMatchingWords(car.getModel().getMake().getName()));
            return Optional.of(car);
        }
        return Optional.empty();
    }

    public boolean deleteCar(int id){
        if(getCar(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private List<String> getMatchingWords(String word){
        List<String> lst = new ArrayList();
        ResponseEntity<MatchingWords[]> response = template.getForEntity(String.format(matchingWordURL, word), MatchingWords[].class);
        List<String> collect;
        if(response.getBody().length>0)
            collect = Arrays.stream(response.getBody()).map(MatchingWords::getWord).collect(Collectors.toList());
        else
            collect = new ArrayList<>();
        return collect;
    }

}

class MatchingWords{
    private String word;
    private int score;
    private String numSyllables;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNumSyllables() {
        return numSyllables;
    }

    public void setNumSyllables(String numSyllables) {
        this.numSyllables = numSyllables;
    }
}
