package com.corelia.library.service;



import com.corelia.library.mapper.AuthorMapper;
import com.corelia.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


}
