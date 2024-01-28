package de.neonique.persistence.source;

import java.util.List;

public interface SourceManager {

    List<String> getTargetFilenames();
    String getPath();
}
