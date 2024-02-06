package br.com.cubosacademi.apirest.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cubosacademi.apirest.apirest.models.Produto;
import br.com.cubosacademi.apirest.apirest.repository.ProdutoRepository;
import br.com.cubosacademi.apirest.apirest.utils.ResponseHendler;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired // cria uma instancia do produtoRepository, sempre que quisermos usar.
    private ProdutoRepository produtoRepository;

    // exibir uma coleção de produtos
    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    // consulta um produto
    @GetMapping("/{id}") // quando coloca entre chaves, o spring entende que parametro
    public ResponseEntity<Object> obter(@PathVariable Integer id) {
        // através do response conseguirmos definir o status code
        // Esse objeto pode ser opcional, oque isso quer dizer, pode ser não encontre o
        // produto também
        Optional<Produto> produto = produtoRepository.findById(id);

        if (!produto.isPresent()) {
            // retorna o status code 404, por que não encontrou nenhum
            return ResponseHendler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(produto.get(), HttpStatus.OK);
    }

    // cadastrar um produto
    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid Produto produto) {
        // preciso dizer que Produto vai vim no body da requisição.

        Produto novoProduto = produtoRepository.save(produto);
        return new ResponseEntity<Object>(novoProduto, HttpStatus.CREATED);
    }

    // editar um produto
    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        Optional<Produto> produtoAntigo = produtoRepository.findById(id);

        if (!produtoAntigo.isPresent()) {
            // retorna o status code 404, por que não encontrou nenhum
            return ResponseHendler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        Produto atualizaProduto = produtoAntigo.get();

        atualizaProduto.setNome(produto.getNome());
        atualizaProduto.setValor(produto.getValor());
        atualizaProduto.setDescricao(produto.getDescricao());

        produtoRepository.save(atualizaProduto);

        return ResponseEntity.noContent().build();
    }

    // excluir um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Integer id) {
        Optional<Produto> produtoADeletar = produtoRepository.findById(id);

        if (!produtoADeletar.isPresent()) {
            // retorna o status code 404, por que não encontrou nenhum
            return ResponseHendler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        produtoRepository.delete(produtoADeletar.get());

        return ResponseEntity.noContent().build();
    }
}
