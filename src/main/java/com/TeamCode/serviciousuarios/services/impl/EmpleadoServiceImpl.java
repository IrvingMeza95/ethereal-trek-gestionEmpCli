package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.enums.Cargo;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.repositories.EmpleadoRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import com.TeamCode.serviciousuarios.services.interfaces.IPersonaService;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepo empleadoRepo;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IPersonaService iPersonaService;

    @Override
    public Empleado guardar(Usuario empleado) throws MyException {
        if (empleado.getPassword() == null || empleado.getPassword().equals(""))
            throw new MyException("La contraseña no puede ser nula.");
        Empleado nuevoEmpleado = new Empleado();
        iPersonaService.crear(empleado, nuevoEmpleado);
        nuevoEmpleado.setPassword(empleado.getPassword());
        nuevoEmpleado.setCargo(Cargo.VENDEDOR.name());
        nuevoEmpleado.setSueldo(0D);
        return empleadoRepo.save(nuevoEmpleado);
    }

    @Override
    public Empleado editar(Empleado empleado, String param) throws MyException {
        Empleado empleadoDb = buscarPorIdEmailDniCelular(param);
        iPersonaService.editar(empleadoDb, empleado);
        empleadoDb.setCargo(empleado.getCargo());
        empleadoDb.setSueldo(empleado.getSueldo());
        empleadoDb.setPassword(empleado.getPassword());
        Usuario usuario = iUsuarioService.usuarioMapper(empleadoDb, empleado.getEmail());
        empleadoDb.setPassword(usuario.getPassword());
        return empleadoRepo.save(empleadoDb);
    }

    @Override
    public void eliminar(String param) throws MyException {
        Empleado empleado = buscarPorIdEmailDniCelular(param);
        iUsuarioService.eliminar(empleado.getEmail());
        empleadoRepo.delete(empleado);
    }

    @Override
    public List<Empleado> listar() {
        return empleadoRepo.findAll();
    }

    @Override
    public Empleado buscarPorIdEmailDniCelular(String param) throws MyException {
        Optional<Empleado> empleado = Optional.of(empleadoRepo.buscarPorIdEmailDniCelular(param));
        if (empleado.isEmpty())
            throw new MyException("No se encontró ningún empleado asociado a " + param + ".");
        return empleado.get();
    }

    @Override
    public Empleado cambiarPassword(String password, String param) throws MyException {
        Empleado empleado = buscarPorIdEmailDniCelular(param);
        Usuario usuario = iUsuarioService.cambairPassword(password, param);
        empleado.setPassword(usuario.getPassword());
        return empleadoRepo.save(empleado);
    }

}
