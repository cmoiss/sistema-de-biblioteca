package com.unp.bibliotecavirtual.control;

import com.unp.bibliotecavirtual.Exception.UsuarioNaoEncontrado;
import com.unp.bibliotecavirtual.dto.mapper.UsuarioMapperDTO;
import com.unp.bibliotecavirtual.dto.request.UsuarioRequestDTO;
import com.unp.bibliotecavirtual.dto.response.UsuarioResponseDTO;
import com.unp.bibliotecavirtual.model.Usuario;
import com.unp.bibliotecavirtual.repository.UsuarioRepository;
import com.unp.bibliotecavirtual.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.unp.bibliotecavirtual.dto.mapper.UsuarioMapperDTO.toEntity;
import static com.unp.bibliotecavirtual.dto.mapper.UsuarioMapperDTO.toResponse;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequest){
        Usuario usuario = toEntity(usuarioRequest);
        usuarioService.cadastrar(usuario);
        UsuarioResponseDTO usuarioResponse = toResponse(usuario);
        return ResponseEntity.status(CREATED).body(usuarioResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioID(@PathVariable Long id){
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(UsuarioMapperDTO.toResponse(usuario));
        }catch (UsuarioNaoEncontrado naoEncontrado){
            return ResponseEntity.status(NOT_FOUND).body(naoEncontrado.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuario(){
        List<Usuario> usuarios = usuarioService.buscarTodos();
        List<UsuarioResponseDTO> usuarioResponse = usuarios.stream().map(UsuarioMapperDTO::toResponse).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(usuarioResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> editar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuarioRequest) {
        Usuario usuarioAtualizado = UsuarioMapperDTO.toEntity(usuarioRequest);
        usuarioAtualizado.setId(id);
        Usuario novoUsuario = usuarioService.editar(usuarioAtualizado);
        return ResponseEntity.ok(UsuarioMapperDTO.toResponse(novoUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        try {
            usuarioService.buscarPorId(id);
            return ResponseEntity.noContent().build();
        }catch (UsuarioNaoEncontrado naoEncontrado){
            return ResponseEntity.status(NOT_FOUND).body(naoEncontrado.getMessage());
        }
    } 
}