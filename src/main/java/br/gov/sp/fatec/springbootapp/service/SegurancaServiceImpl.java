package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;


@Service("SegurancaService")
public class SegurancaServiceImpl implements SegurancaService {
    
    @Autowired
    private AutorizacaoRepository autRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {

        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null){
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }
    @Override
    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepo.findAll();
        
    }
    @Override
    public Usuario buscarUsuarioPorId(Long id){
        Optional<Usuario> usuarioOP = usuarioRepo.findById(id);
        if(usuarioOP.isPresent()){
            return usuarioOP.get();
        }  else{
            throw new RuntimeException("usuario nao encontrado");
        }
        
    }
    @Override
    public Usuario buscarUsuarioPorNome(String nome){
        Usuario usuario= usuarioRepo.findByNome(nome);
        if(usuario != null){
            return usuario;
        }  else{
            throw new RuntimeException("usuario nao encontrado");
        }
    
    }
    @Override 
    public Autorizacao buscarAutorizacaoPorNome(String nome){
        Autorizacao autorizacao = autRepo.findByNome(nome);
        if(autorizacao != null){
            return autorizacao;
        }
        throw new RuntimeException("autorizacao não encontrada");
    }
}