package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DecentralizationId implements Serializable {
    public static final String ROLE = "role";
    public static final String MODULE = "module";
    public static final String FUNCTION = "function";

    @ManyToOne
    @JoinColumn(name = Role.ROLE_ID)
    private Role role;
    @ManyToOne
    @JoinColumn(name = Module.MODULE_ID)
    private Module module;
    @ManyToOne
    @JoinColumn(name = Function.FUNCTION_ID)
    private Function function;

    public DecentralizationId() {
    }

    public DecentralizationId(Role role, Module module, Function function) {
        this.role = role;
        this.module = module;
        this.function = function;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DecentralizationId that)) return false;

        if (!getRole().equals(that.getRole())) return false;
        if (!getModule().equals(that.getModule())) return false;
        return getFunction().equals(that.getFunction());
    }

    @Override
    public int hashCode() {
        int result = getRole().hashCode();
        result = 31 * result + getModule().hashCode();
        result = 31 * result + getFunction().hashCode();
        return result;
    }
}
