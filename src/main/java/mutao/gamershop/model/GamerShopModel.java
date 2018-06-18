package mutao.gamershop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gamershopmodel")
public class GamerShopModel extends AbstractEntityClass {
    private static final long serialVersionUID = 9182334921533574136L;
}
