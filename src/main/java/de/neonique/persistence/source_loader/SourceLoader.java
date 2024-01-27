package de.neonique.persistence.source_loader;

import java.nio.file.Path;
import java.util.List;

public interface SourceLoader {

    List<String> getTargetFilenames();
    String getPath();
}
