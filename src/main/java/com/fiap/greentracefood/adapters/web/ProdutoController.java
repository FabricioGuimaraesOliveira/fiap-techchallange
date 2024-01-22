package com.fiap.greentracefood.adapters.web;

import com.fiap.greentracefood.adapters.web.request.produto.ProdutoRequestDTO;
import com.fiap.greentracefood.adapters.web.response.cliente.ClienteResponseDTO;
import com.fiap.greentracefood.adapters.web.response.produto.ProdutoResponseDTO;
import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.Categoria;
import com.fiap.greentracefood.application.port.incoming.ProdutoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@Tag(name = "produto", description = "API responsável pelo controle de produtos.")
public class ProdutoController {

    private final ModelMapper modelMapper;
    private final ProdutoUseCase produtoUseCase;
    public ProdutoController(ModelMapper modelMapper, ProdutoUseCase produtoUseCase) {
        this.modelMapper = modelMapper;
        this.produtoUseCase = produtoUseCase;
    }
    @Operation(summary = "Cadastrar um Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar o Produto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o Produto",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar o Produto",
                    content = @Content) })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO cadastrarProduto(@Valid @RequestBody ProdutoRequestDTO request) {
        Produto produto = modelMapper.map(request, Produto.class);
        return modelMapper.map(produtoUseCase.cadastrar(produto), ProdutoResponseDTO.class);
    }

    @Operation(summary = "Consultar um Produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao consultar o Produto por ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content) })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO consultarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoUseCase.consultar(id);
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    @Operation(summary = "Atualizar um Produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o Produto por ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content) })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO atualizarProdutoPorId(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO request) {
        Produto produtoAtualizado = modelMapper.map(request, Produto.class);
        Produto produto = produtoUseCase.atualizar(id, produtoAtualizado);
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    @Operation(summary = "Excluir um Produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso ao excluir o Produto por ID"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content) })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProdutoPorId(@PathVariable Long id) {
        produtoUseCase.excluir(id);
    }

    @Operation(summary = "Consultar Produtos Paginados por Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao consultar Produtos por Categoria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Produtos não encontrados para a Categoria",
                    content = @Content) })
    @GetMapping("/categoria/{categoria}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProdutoResponseDTO> consultarProdutosPorCategoria(
            @PathVariable Categoria categoria,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {


        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> produtos = produtoUseCase.consultaByCategoria(categoria, pageable);

        return produtos.map(produto -> modelMapper.map(produto, ProdutoResponseDTO.class));
    }

}
