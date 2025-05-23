package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final TreeSet<Note> notes;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, address, tags);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tags.addAll(tags);
        this.notes = new TreeSet<>();
    }

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Address address, Set<Tag> tags, TreeSet<Note> notes) {
        requireAllNonNull(name, phone, address, tags);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tags.addAll(tags);
        this.notes = notes;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Retrieves the ordered set of notes associated with the patient
     * @return an immutable navigable note set, which throws {@code UnsupportedOperationException}
     *     if modification is attempted.
     */
    public TreeSet<Note> getNotes() {
        return this.notes;
    }

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().toString().toLowerCase()
                .equals(getName().toString().toLowerCase());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return name.equals(otherPatient.name)
                && phone.equals(otherPatient.phone)
                && address.equals(otherPatient.address)
                && tags.equals(otherPatient.tags)
                && notes.equals(otherPatient.notes);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, tags, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("address", address)
                .add("tags", tags)
                .add("notes", notes)
                .toString();
    }

}
