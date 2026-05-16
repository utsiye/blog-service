package dev.utsiye.blog_service.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import dev.utsiye.blog_service.application.dto.Book.BookDTO;
import dev.utsiye.blog_service.application.dto.Book.BookSummaryDTO;
import dev.utsiye.blog_service.application.dto.Book.BookCreationRequestDTO;
import dev.utsiye.blog_service.application.dto.Book.BookUpdateRequestDTO;
import dev.utsiye.blog_service.application.dto.Book.BookListFilterRequestDTO;
import dev.utsiye.blog_service.presentation.dto.BookListFilterDTO;
import dev.utsiye.blog_service.application.interactors.book.CreateBookInteractor;
import dev.utsiye.blog_service.application.interactors.book.ListBooksInteractor;
import dev.utsiye.blog_service.application.interactors.book.GetBookDetailsInteractor;
import dev.utsiye.blog_service.application.interactors.book.UpdateBookInteractor;
import dev.utsiye.blog_service.application.interactors.book.SoftDeleteBookInteractor;


@RestController
@RequestMapping("/books")
public class BookController {
	private final CreateBookInteractor createBookUseCase;
	private final ListBooksInteractor listBooksUseCase;
	private final GetBookDetailsInteractor getBookDetailsUseCase;
	private final UpdateBookInteractor updateBookUseCase;
	private final SoftDeleteBookInteractor softDeleteBookUseCase;

	public BookController(
			CreateBookInteractor createBookUseCase,
			ListBooksInteractor listBooksUseCase,
			GetBookDetailsInteractor getBookDetailsUseCase,
			UpdateBookInteractor updateBookUseCase,
			SoftDeleteBookInteractor softDeleteBookUseCase
	) {
		this.createBookUseCase = createBookUseCase;
		this.listBooksUseCase = listBooksUseCase;
		this.getBookDetailsUseCase = getBookDetailsUseCase;
		this.updateBookUseCase = updateBookUseCase;
		this.softDeleteBookUseCase = softDeleteBookUseCase;
	}

	@PostMapping("/")
	public ResponseEntity<BookDTO> createBook(@RequestBody BookCreationRequestDTO request) {
		BookDTO response = createBookUseCase.execute(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/")
	public ResponseEntity<List<BookSummaryDTO>> listBooks(BookListFilterDTO bookFilters) {
		BookListFilterRequestDTO filter = new BookListFilterRequestDTO(bookFilters.getCategoryId(),
        bookFilters.getAuthor(), bookFilters.getOffset(), bookFilters.getLimit());
		List<BookSummaryDTO> response = listBooksUseCase.execute(filter);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
		BookDTO response = getBookDetailsUseCase.execute(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequestDTO request) {
		BookDTO response = updateBookUseCase.execute(id, request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		softDeleteBookUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}
}
