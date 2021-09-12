package com.Nghiem.tgu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nghiem.tgu.Model.Carousel;

import com.Nghiem.tgu.Repository.CarouselReponsitory;

import Exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pic/")
public class CarouselController {
	@Autowired
 CarouselReponsitory carouselReponsitory;
	
	
	@GetMapping("/show_pic")
	public List<Carousel> getAllCarousel(){
		return carouselReponsitory.findAll();
	}
	
	@PostMapping("/show_pic")
	public Carousel createCarousel(@RequestBody Carousel carousel) {
		return carouselReponsitory.save(carousel);
	}

	@GetMapping("/show_pic/{id}")
	public ResponseEntity<Carousel> getKhoaById(@PathVariable Long id) {
		Carousel carousel = carouselReponsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PIC not exist with id :" + id));
		return ResponseEntity.ok(carousel);
	}
	
		@PutMapping("/show_pic/{id}")
		public ResponseEntity<Carousel> updateCarousel(@PathVariable Long id, @RequestBody Carousel carouselDetails){
			Carousel carousel = carouselReponsitory.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Pic not exist with id :" + id));
			
			carousel.setHinh(carouselDetails.getHinh());
			carousel.setText1(carouselDetails.getText1());
			carousel.setText2(carouselDetails.getText2());
			carousel.setCreatedAt(carouselDetails.getCreatedAt());
			carousel.setUpdatedAt(carouselDetails.getUpdatedAt());
			Carousel upCarousel = carouselReponsitory.save(carousel);
			return ResponseEntity.ok(upCarousel);
		}
		
		// delete  rest api
		@DeleteMapping("/show_pic/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteCarousel(@PathVariable Long id){
			Carousel carousel = carouselReponsitory.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Pic not exist with id :" + id));
			
			carouselReponsitory.delete(carousel);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
