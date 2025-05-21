

package br.edu.iftm.livro.controller;

import br.edu.iftm.ivro.model.Livro;
import br.edu.iftm.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping
    public List<Livro> listarLivros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor
    ) {
        if (titulo != null) {
            return repository.findByTitulo(titulo);
        }
        if (autor != null) {
            return repository.findByAutor(autor);
        }
        return repository.findAll();
    }

    // GET /livros/{isbn}
    @GetMapping("/{isbn}")
    public Livro buscarPorIsbn(@PathVariable String isbn) {
        return repository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("O Livro nao esta aqui"));
    }

    // POST /livros
    @PostMapping
    public void cadastrarLivro(@RequestBody Livro livro) {
        repository.save(livro);
    }
}