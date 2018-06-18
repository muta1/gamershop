package mutao.gamershop.interceptor;

import static br.com.caelum.vraptor.view.Results.json;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.jpa.JPATransactionInterceptor;


@Intercepts(before = JPATransactionInterceptor.class)
@RequestScoped
public class ExceptionInterceptor {
	

	private final Result result;
	protected final String ERROR_TAG = "API-Error";

	
	@Deprecated
	protected ExceptionInterceptor() {
		this(null);
	}

	@Inject
	public ExceptionInterceptor(Result result) {
		this.result = result;
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {
		try {
			stack.next();
		} catch (Exception e) {			
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
	}
}