package com.poisonedouyth.springkotlinhexagonal.adapter.outgoing

import com.poisonedyouth.springkotlinhexagonal.model.User
import com.poisonedyouth.springkotlinhexagonal.port.outgoing.UserRepository
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.Optional

@Entity
data class UserEntity(
    @Id
    @GeneratedValue
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
)

@Repository
@Transactional
class UserJpaRepository : UserRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findAll(): List<User> {
        val cb: CriteriaBuilder = entityManager.criteriaBuilder
        val cq: CriteriaQuery<UserEntity> = cb.createQuery(UserEntity::class.java)
        val rootEntry: Root<UserEntity> = cq.from(UserEntity::class.java)
        val all: CriteriaQuery<UserEntity> = cq.select(rootEntry)
        val allQuery: TypedQuery<UserEntity> = entityManager.createQuery(all)
        return allQuery.resultList.map { it.toUser() }
    }

    override fun findById(id: Long): Optional<User> {
        return Optional.ofNullable(entityManager.find(UserEntity::class.java, id).toUser())
    }

    override fun save(user: User): User {
        val userEntity = user.toUserEntity()
        entityManager.persist(userEntity)
        entityManager.flush()
        return userEntity.toUser()
    }

    override fun delete(user: User) {
        entityManager.remove(user)
    }

    fun UserEntity.toUser() = User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.lastName,
        age = this.age
    )

    fun User.toUserEntity() = UserEntity(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age
    )
}
