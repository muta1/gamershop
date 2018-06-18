package mutao.gamershop.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import mutao.gamershop.model.GamerShopModel;

@RequestScoped
public class GamerShopDAO extends AbstractDaoClass<GamerShopModel> {

	@Inject
	public GamerShopDAO(EntityManager em) {
		super(em);
	}

}
