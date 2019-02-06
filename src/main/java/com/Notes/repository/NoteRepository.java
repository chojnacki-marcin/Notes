package com.Notes.repository;

import com.Notes.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface NoteRepository extends CrudRepository<Note, Long> {
    Iterable<Note> findAllByAccountId(long accountId);
}
