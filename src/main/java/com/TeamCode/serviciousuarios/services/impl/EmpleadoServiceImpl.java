package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.clientes.impl.UsuariosRestImpl;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.repositories.EmpleadoRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepo empleadoRepo;
    @Autowired
    private UsuariosRestImpl usuariosRest;

    @Override
    public Empleado guardar(Empleado empleado) throws MyException {
        if (empleado.getPassword() == null || empleado.getPassword().equals(""))
            throw new MyException("La contraseña no puede ser nula.");
        return empleadoRepo.save(empleado);
    }

    @Override
    public Optional<Empleado> buscarPorId(String id) {
        return empleadoRepo.findById(id);
    }

    @Override
    public Empleado editar(Empleado empleado, String id) {
        Empleado empleadoDb = buscarPorId(id).get();
        empleadoDb.setNombre(empleado.getNombre());
        empleadoDb.setApellido(empleado.getApellido());
        empleadoDb.setCelular(empleado.getCelular());
        empleadoDb.setEmail(empleado.getEmail());
        empleadoDb.setDni(empleado.getDni());
        empleadoDb.setPais(empleado.getPais());
        empleadoDb.setFechaNac(empleado.getFechaNac());
        empleadoDb.setCargo(empleado.getCargo());
        empleadoDb.setSueldo(empleado.getSueldo());
        empleadoDb.setPassword(empleado.getPassword());
        usuariosRest.editar(empleadoDb, empleado.getEmail());
        return empleadoRepo.save(empleadoDb);
    }

    @Override
    public void eliminarPorId(String id) {
        empleadoRepo.deleteById(id);
    }

    @Override
    public void eliminarPorEmail(String email) {
        usuariosRest.eliminarPorEmail(email);
        Empleado empleado = buscarPorEmail(email);
        empleadoRepo.delete(empleado);

    }

    @Override
    public List<Empleado> listar() {
        return empleadoRepo.findAll();
    }

    @Override
    public Empleado buscarPorEmail(String email) {
        return empleadoRepo.findByEmail(email);
    }

}
