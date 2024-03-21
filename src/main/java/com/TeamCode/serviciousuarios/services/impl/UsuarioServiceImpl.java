package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.repositories.UsuariosRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuariosRepo usuariosRepo;
    private final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public Usuario guardar(Usuario persona) throws MyException {
        validarDatos(persona);
        return usuariosRepo.save(persona);
    }

    private void validarDatos(Usuario persona) throws MyException {
        if (!validarEmail(persona.getEmail()))
            throw new MyException("La dirección de correo no es válida.");
        if (persona.getDni().isEmpty() || persona.getDni().equals(" "))
            throw new MyException("El dni no es válido.");
        if (persona.getCodigoDeLlamada().isEmpty() || persona.getCodigoDeLlamada().equals(" "))
            throw new MyException("El codigo de llamada no es válido.");
        if (persona.getCelular().isEmpty() || persona.getCelular().equals(" "))
            throw new MyException("El celular no es válido.");
        if (persona.getPais().isEmpty() || persona.getPais().equals(" "))
            throw new MyException("El pais no es válido.");
        if (persona.getRoles().isEmpty())
            throw new MyException("Es necesario asignar al menos un role al usuario.");
    }

    @Override
    public Usuario registrarEmpleado(Usuario empleado) throws MyException {
        if (empleado.getPassword() == null || empleado.getPassword().equals(""))
            throw new MyException("La contraseña no puede ser nula.");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        empleado.setPassword(encoder.encode(empleado.getPassword()));
        return guardar(empleado);
    }

    @Override
    public Usuario registrarCliente(Usuario cliente) throws MyException {
        return guardar(cliente);
    }

    @Override
    public void eliminar(String param) throws MyException {
        Usuario usuario = buscarPorIdEmailDniCelular(param);
        usuariosRepo.delete(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuariosRepo.findAll();
    }

    @Override
    public Usuario editar(Usuario usuario, String param) throws MyException {
        Usuario usuarioBd = buscarPorIdEmailDniCelular(param);
        usuarioBd.setNombre(usuario.getNombre());
        usuarioBd.setApellido(usuario.getApellido());
        usuarioBd.setEmail(usuario.getEmail());
        usuarioBd.setCelular(usuario.getCelular());
        usuarioBd.setDni(usuario.getDni());
        usuarioBd.setEnabled(usuario.getEnabled());
        usuarioBd.setIntentos(usuario.getIntentos());
        usuarioBd.setPais(usuario.getPais());
        usuarioBd.setCodigoDeLlamada(usuario.getCodigoDeLlamada());
        return usuariosRepo.save(usuarioBd);
    }

    @Override
    public Usuario buscarPorIdEmailDniCelular(String param) throws MyException {
        Optional<Usuario> usuario = Optional.of(usuariosRepo.buscarPorIdEmailDniCelular(param));
        if (!usuario.isPresent())
            throw new MyException("No se encontró ningún usuario asociado a " + param + ".");
        return usuario.get();
    }

    @Override
    public Usuario cambairPassword(String password, String param) throws MyException {
        Usuario usuarioBd = buscarPorIdEmailDniCelular(param);
        if (usuarioBd.getPassword() == null && usuarioBd.getPassword().isEmpty())
            throw new MyException("Contraseña inválida.");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuarioBd.setPassword(encoder.encode(password));
        return usuariosRepo.save(usuarioBd);
    }

    private Boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Usuario usuarioMapper(Persona persona, String email) throws MyException {
        Usuario usuario = new Usuario();
        usuario.setNombre(persona.getNombre());
        usuario.setApellido(persona.getApellido());
        usuario.setEmail(persona.getEmail());
        usuario.setDni(persona.getDni());
        usuario.setCelular(persona.getCelular());
        usuario.setEnabled(persona.getEnabled());
        usuario.setIntentos(0);
        usuario.setPais(persona.getPais());
        usuario.setCodigoDeLlamada(persona.getCodigoDeLlamada());
        return editar(usuario,email);
    }

}
