package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ResturantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Resturant;
import kr.co.fastcampus.eatgo.domain.ResturantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class ResturantController {

    @Autowired
    private ResturantService resturantService;

    @GetMapping("/resturants")
    public List<Resturant> list(){

        List<Resturant> resturants= resturantService.getResturants();
        return resturants;
    }

    @GetMapping("/resturants/{id}")
    public Resturant detail(@PathVariable("id") Long id){
        Resturant resturant=resturantService.getResturant(id);
        return resturant;

    }
    @PostMapping("/resturants")
    public ResponseEntity<?> create(@Valid @RequestBody Resturant resource) throws URISyntaxException {
        String name = resource.getName();
        String address=resource.getAddress();
        Resturant resturant =resturantService.addResturant(
                Resturant.builder()
                        .name(name)
                        .address(address)
                        .build());
        URI location =new URI("/resturants/"+resturant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/resturants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Resturant resource){
        String name =resource.getName();
        String address = resource.getAddress();
        resturantService.updateResturant(id, name, address);
        return "{}";
    }
}