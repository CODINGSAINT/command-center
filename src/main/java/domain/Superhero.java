package domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
public record Superhero( Long id,
                        String name,
                        String prefix,
                        String suffix,
                        String power) {
}
