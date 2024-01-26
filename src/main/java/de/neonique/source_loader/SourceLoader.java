package de.neonique.source_loader;

import java.nio.file.Path;
import java.util.List;

public interface SourceLoader {

    List<String> getTargetFilenames();
    Path getPath();
}
