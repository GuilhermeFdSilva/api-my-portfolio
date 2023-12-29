package br.com.francaguilherme.myportfolio.helpers.initializer;

import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DataInitializerTest {
    @Mock
    private AdminRepository repository;

    @InjectMocks
    private DataInitializer initializer;

    @Test
    void testRun() {
        when(repository.existsById(1L)).thenReturn(false);

        initializer.run();

        verify(repository, times(1)).save(any());
    }
}
