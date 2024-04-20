package it.sosinski.finances.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/secured/finance-category")
@CrossOrigin("https://mojhome.vercel.app")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories() {
		final List<CategoryDto> categoriesDto = categoryService.findAll();
		return ResponseEntity.ok(categoriesDto);
	}

}
