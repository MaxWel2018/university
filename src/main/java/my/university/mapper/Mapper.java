package my.university.mapper;

public interface Mapper<DOMAIN, ENTITY> {
    DOMAIN mapEntityToDomain(ENTITY entity);

    ENTITY mapDomainToEntity(DOMAIN domain);

}
