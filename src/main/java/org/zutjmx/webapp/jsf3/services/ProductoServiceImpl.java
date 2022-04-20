package org.zutjmx.webapp.jsf3.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.zutjmx.webapp.jsf3.entities.Categoria;
import org.zutjmx.webapp.jsf3.entities.Producto;
import org.zutjmx.webapp.jsf3.repositories.CrudRepository;
import org.zutjmx.webapp.jsf3.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER","ADMIN"})
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> repositoryCategoria;

    @Override
    @PermitAll
    public List<Producto> listar() {
        return repository.listar();
    }

    @RolesAllowed({"USER","ADMIN"})
    @Override
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @RolesAllowed({"USER","ADMIN"})
    @Override
    public List<Categoria> listarCategorias() {
        return repositoryCategoria.listar();
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.ofNullable(repositoryCategoria.porId(id));
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Producto> listarPorNombre(String nombre) {
        return repository.listarPorNombre(nombre);
    }
}
