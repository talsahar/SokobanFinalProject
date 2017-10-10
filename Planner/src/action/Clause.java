/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package action;

import java.util.LinkedList;

public abstract class Clause extends Predicate {

	private LinkedList<Predicate> predicates;

	public Clause(Predicate... predicates) {
		super("And", "", "");
		this.predicates = new LinkedList<>();
		for (Predicate p : predicates)
			this.predicates.add(p);

		updateDescription();
	}

	public void addLast(Predicate p) {
		predicates.addLast(p);
		updateDescription();

	}

	public void addFirst(Predicate p) {
		predicates.addFirst(p);
		updateDescription();

	}

	public Predicate getPredicateByType(String type) {

		for (Predicate p : predicates)
			if (p.type.equals(type))
				return p;
		return null;
	}

	public Predicate getPredicateByValue(String value) {

		for (Predicate p : predicates)
			if (p.value.equals(value))
				return p;
		return null;
	}

	public Predicate getPredicateByID(String id) {

		for (Predicate p : predicates)
			if (p.id.equals(type))
				return p;
		return null;

	}

	public Predicate getPredicateByTypeAndValue(String type, String id) {

		for (Predicate p : predicates)
			if ((p.type.equals(type)) && (p.id.equals(id)))
				return p;
		return null;

	}

	public LinkedList<Predicate> getPredicates() {
		return predicates;
	}

	public boolean isEmpty() {
		return predicates.isEmpty();
	}

	public boolean isSatisfied(Clause clause) {
		for (Predicate p : clause.getPredicates())
			if (!isSatisfied(p))
				return false;
		return true;

	}

	@Override
	public boolean isSatisfied(Predicate p) {

		for (Predicate pr : predicates)
			if (pr.isSatisfied(p))
				return true;
		return false;

	}

	public void update(Clause effect) {

		effect.predicates.forEach(p -> predicates.removeIf(pr -> p.contradicts(pr)));
		predicates.addAll(effect.predicates);
		updateDescription();

	}

	private void updateDescription() {
		value = "{";
		for (Predicate p : predicates)
			value += p.toString() + " & ";
		value += "}";
	}

	public void update(Predicate effect) {
		predicates.removeIf(pr -> effect.contradicts(pr));
		predicates.add(effect);
		updateDescription();

	}

}
