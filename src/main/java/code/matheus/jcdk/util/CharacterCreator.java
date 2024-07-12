package code.matheus.jdck.util;

import code.matheus.jdck.character.Character;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public final class CharacterCreator {
    public static <T extends Character> @NotNull T create(@NotNull String name, @NotNull Character.Gender gender, @NotNull Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(String.class, Character.Gender.class);
            constructor.setAccessible(true);
            return constructor.newInstance(name, gender);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Argument invalid " + e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Constructor not found " + e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Access to constructor negate");
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
