package br.fema.edu.squidconf.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.Objects;

public class TimeRule {
    @JsonFormat(pattern = "hh:mm")
    private LocalTime begin;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime end;
    private boolean deny;
    private String nome;

    public TimeRule() {
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public boolean isDeny() {
        return deny;
    }

    public void setDeny(boolean deny) {
        this.deny = deny;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeRule)) return false;
        TimeRule timeRule = (TimeRule) o;
        return deny == timeRule.deny &&
                Objects.equals(begin, timeRule.begin) &&
                Objects.equals(end, timeRule.end) &&
                Objects.equals(nome, timeRule.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, deny, nome);
    }
}
