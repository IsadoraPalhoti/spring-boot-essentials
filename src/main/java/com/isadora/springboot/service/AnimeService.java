package com.isadora.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.isadora.springboot.domain.Anime;

@Service
public class AnimeService {
	private static List<Anime> animes;
	static {
		 animes = new ArrayList<>(List.of(new Anime(1L, "DBZ"), new Anime(2L, "Berserk")));
	}

	// private final AnimeRepository animeRepository;
	public List<Anime> listAll() {
		return animes;
	}

	// private final AnimeRepository animeRepository;
	public Anime findById(long id) {
		return animes.stream()
				.filter(anime -> anime.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not fund"));
	}

	public Anime save(Anime anime) {
		anime.setId(ThreadLocalRandom.current().nextLong(3,100));
		animes.add(anime);
		return anime;
	}
	
	public void delete(long id) {
		animes.remove(findById(id));
	}

	public void replace(Anime anime) {
		delete(anime.getId());
		animes.add(anime);
	}
}
