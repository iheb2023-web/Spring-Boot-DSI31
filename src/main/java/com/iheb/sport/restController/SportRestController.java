package com.iheb.sport.restController;

import java.util.List;

import com.iheb.sport.dto.SportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iheb.sport.entities.sport;
import com.iheb.sport.service.SportService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SportRestController {
	@Autowired
	SportService sportService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<SportDTO> getAllSports(){
		return sportService.getAllSport();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public SportDTO getSportById(@PathVariable("id") Long id) {
		return sportService.getSport(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public SportDTO createSport(@RequestBody SportDTO sp) {
		return sportService.saveSport(sp)
;	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public SportDTO updateSport(@RequestBody SportDTO sp) {
		return sportService.updateSport(sp);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteSport(@PathVariable("id") Long id) {
		sportService.deletSportById(id);
	}

	@RequestMapping(value = "/sportsgen/{idGen}", method = RequestMethod.GET)
	public List<sport> getSportByCatId(@PathVariable("idGen") Long idGen) {
		return sportService.findByGenreIdGen(idGen);
	}

	@RequestMapping(value="/sportByName/{nom}",method = RequestMethod.GET)
	public List<sport> findByNomSportContains(@PathVariable("nom") String nom) {
		return sportService.findByNomSport(nom);
	}
	

}
