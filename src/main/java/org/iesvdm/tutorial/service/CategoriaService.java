package org.iesvdm.tutorial.service;


import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.exception.EntityNotFoundException;
import org.iesvdm.tutorial.exception.NotCouplingIdException;
import org.iesvdm.tutorial.repository.CategoriaRepository;
import org.iesvdm.tutorial.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        this.categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map(i -> {
                    if (id.equals(categoria.getId())) return this.categoriaRepository.save(categoria);
                    else throw new NotCouplingIdException(id, categoria.getId(), Categoria.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(t -> {this.categoriaRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }
}
