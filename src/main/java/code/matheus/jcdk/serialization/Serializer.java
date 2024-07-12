package code.matheus.jdck.serialization;

import code.matheus.jdck.character.Character;
import code.matheus.jdck.impl.CharacterImpl;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface Serializer {
    void serialize(@NotNull CharacterImpl character, @NotNull String path);

    @NotNull String serialize(@NotNull CharacterImpl character);

    @NotNull CharacterImpl deserializer(@NotNull File file);

    @NotNull Character deserializer(@NotNull String string);
}
