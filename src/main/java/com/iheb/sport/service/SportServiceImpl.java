package com.iheb.sport.service;


import java.util.List;
import java.util.stream.Collectors;

import com.iheb.sport.dto.SportDTO;
import com.iheb.sport.repos.GenreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iheb.sport.entities.Genre;
import com.iheb.sport.entities.sport;
import com.iheb.sport.repos.SportRepository;

@Service
public class SportServiceImpl  implements SportService{
	
	 @Autowired
	 SportRepository sportRepository;

	 @Autowired
	 GenreRepository genreRepository;

	 @Autowired
	ModelMapper modelMapper;

	//@PreAuthorize("hasAuthority('ADMIN')") // fonctionne avec @EnableMethodSecurity(prePostEnabled = true) dans securityconfig
	@Override
	public SportDTO saveSport(SportDTO s) {


		 return convertEntityToDto(sportRepository.save(convertDtoToEntity(s)));
	 }
	 @Override
	 public void deletSport(sport s) {
		  sportRepository.delete(s);
	 }
	 @Override
	 public void deletSportById(long id) {
		 sportRepository.deleteById(id);
	 }
	 @Override
	 public SportDTO getSport(long id) {
		 return convertEntityToDto(sportRepository.findById(id).get());
	 }

	 @Override
	 public List<SportDTO> getAllSport() {
         /*recupere tout les entites sport de bd et les convertire en objets sportDto puis renvoi la liste de ces objets DTO*/
		 return sportRepository.findAll().stream()
				 .map(this::convertEntityToDto)
				 .collect(Collectors.toList());
		 //OU BIEN
			/*List<Produit> prods = produitRepository.findAll();
			List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
			for (Produit p : prods)
			listprodDto.add(convertEntityToDto(p));
			return listprodDto;*/
	 }
	 @Override
	 public SportDTO updateSport(SportDTO p) {

		 return convertEntityToDto(sportRepository.save(convertDtoToEntity(p)));
		 }
	 
	  @Override
	 public List<sport> findByNomSport(String nom) { 
	  return sportRepository.findByNomSport(nom); 
	 } 
	 
	  
	 public List<sport> findByNomSportContains(String nom) { 
	  return sportRepository.findByNomSportContains(nom); 
	 } 
	 
	  
	 public List<sport>findByNomDate(String nom, int date) { 
	  return sportRepository.findByNomAndYear(nom,date); 
	 } 
	  
	  
	 public List<sport> findByGenre(Genre genre) { 
	  return sportRepository.findByGenre(genre); 
	 } 
	 
	 public List<sport> findByGenreIdGen(Long id) { 
	  return sportRepository.findByGenreIdGen(id); 
	 } 
	 
	 public List<sport> findByOrderByNomSportAsc() { 
	  return sportRepository.findByOrderByNomSportAsc(); 
	 } 
	 
	 public List<sport> trierSportNomDate() { 
	  return sportRepository.trierSportsNomsDateFondation(); 
	 }

	public SportDTO convertEntityToDto(sport sp) {
		/*SportDTO sportDTO = new SportDTO();
		sportDTO.setIdSport(sp.getIdSport());
		sportDTO.setNomSport(sp.getNomSport());
		sportDTO.setDescription(sp.getDescription());
		sportDTO.setDateFondation(sp.getDateFondation());
		sportDTO.setGenre(sp.getGenre());
			return sportDTO;*/
		/*
		 return SportDTO.builder()
				.idSport(sp.getIdSport())
				.nomSport(sp.getNomSport())
				.Description(sp.getDescription())
				.dateFondation(sp.getDateFondation())
				.genre(sp.getGenre())
				 //.nomGen(sp.getGenre().getNomGen())
				.build();
				*/
		 /* Avec Model Mapper elle devient commme ca */
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			SportDTO sportDTO =  modelMapper.map(sp,SportDTO.class);
			return sportDTO;
	}


	public sport convertDtoToEntity(SportDTO sdt) {

		 sport sp = new sport();
		/*
		sp.setIdSport(sdt.getIdSport());
		sp.setNomSport(sdt.getNomSport());
		sp.setDescription(sdt.getDescription());
		sp.setDateFondation(sdt.getDateFondation());
		sp.setGenre(sdt.getGenre());
			return sp;

		 */
		 sp = modelMapper.map(sdt, sport.class);
		 	return sp;
	}


	public Genre saveGenre(Genre s) {

		return genreRepository.save(s);
	}



}
