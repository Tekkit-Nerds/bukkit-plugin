package me.tekkitnerds.controlcenter.regelbuch;
//@author niklas
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class RegelbuchCommand implements CommandExecutor {

    private controlcenter plugin;

    public RegelbuchCommand(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }
    
    public RegelbuchCommand(Player p){
        GiveRegelbuch(p);
    }

    private boolean GiveRegelbuch(Player p) {
        ItemStack buch = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) buch.getItemMeta();
        meta.setAuthor("Tekkit-Nerds-Verwaltung");
        meta.setTitle("Die Grundregeln");
        meta.addPage("\nDie Grundregeln\n\nVersion: 0.1\n\n\nvon: Tekkit-Nerds-Verwaltung");
        meta.addPage("1. Verhalten\n\n"
                + "a. Hinter jedem Minecraft-Spieler steckt ein Mensch wie du und ich.\n"
                + "b. Wir behandeln jeden Respektvoll und Höflich.\n"
                + "c. Auf allen Diensten akzeptieren wir nur Deutsch und Englisch als Sprache.\n"
                + "d. Rechts- und oder Sittenwidrige Schriften, Zeichen, Gesten und Gebäude sind strengstens Verboten und werden ohne Verwarnung bestraft.");
        meta.addPage("2. Stromversorgungen/ Öffentliche Einrichtungen\n\n"
                + "a Hausanschlüsse werden, soweit nicht anders für die einzelnen Gebiete angegeben, auf der Versorgerseite mit einem MFSU abgesichert.\nb. Die aus dem Anschluss gelieferte Stromstärke kann Hochspannung entsprechen. Trafos zur Spannungskonvertierung sind selber zu bauen.\nc. Das manipulieren, sabotieren oder beschädigen von Öffentlichen Einrichtungen ist verboten.\nd. Industriegebäude werden mit einem MFSU (512EU/t) versorgt\ne. Stadt-Grundstücke werden mit einem MFE (XXEU/t) versorgt");
        meta.addPage("3. Dauerhafte Schaltungen\n\n"
                + "a. Dimension Anker dürfen nur so platziert werden, dass das eigene Grundstück betroffen ist.\n"
                + "b. Pro Grundstück im Industriegebiet sind 2 Dimension Anker erlaubt mit einer Größe von 1x1.\n"
                + "c. Bei tollen Industrie-Projekten wird auf Antrag ein größerer Anker genehmigt.\n"
                + "d. Alle Schaltungen sind auf optimierungspotenzial zu überprüfen und ggf. zu optimieren.");
        meta.addPage("4. Atomtechnik / TNT\n\n"
                + "a. Sprengstoff darf Serverweit nicht eingesetzt werden. Dazu zählt unter anderem TNT und die Nuke.\n"
                + "b. Atomtechnik für die Stromerzeugung darf nur von Admins gebaut und betrieben werden.\n"
                + "i. Die Technik alleine ist kein Verstoß gegen die Regeln, erst das bestücken der Anlage mit potenziell Atomaren-Elementen ist verboten!");
        meta.addPage("4. Atomtechnik / TNT\n\n"
                + "a. Sprengstoff darf Serverweit nicht eingesetzt werden. Dazu zählt unter anderem TNT und die Nuke.\n"
                + "b. Atomtechnik für die Stromerzeugung darf nur von Admins gebaut und betrieben werden.\n"
                + "i. Die Technik alleine ist kein Verstoß gegen die Regeln, erst das bestücken der Anlage mit potenziell Atomaren-Elementen ist verboten!");
        meta.addPage("5. Farming - Gebiete\n\n"
                + "a.An verschiedenen Stellen auf der Map werden Farming-Gebiete kostenfrei angeboten.\n"
                + "b.Die Farming-Gebiete dürfen nur in Haushaltsüblichen Mengen verwendet werden. Gebt auch anderen Spielern die Chance etwas zu bekommen!\n"
                + "c.In einzelnen Gebieten ist das Töten der Tiere verboten.Genaue Regeln stehen an den Eingängen zu dem Gebiet.\n"
                + "d.Für Größere Gebiete werden Ergänzende Regeln im Forum veröffentlicht.");
        meta.addPage("6. Clientmods\n\n"
                + "a. Die jeweils von „Tekkit Lite“ verwendeten Module sind in der jeweils von „Tekkit Lite“ verwendeten Version erlaubt.\n"
                + "b.Ergänzende Plugins / Mods / Module sind grundsätzlich Verboten.\n"
                + "c.Grafische Optimierungen, wie Skinpacks oder der Shader-Mod sind erlaubt.");
        meta.addPage("7. Admins\n\n"
                + "a. Admins\n"
                + "i. Jeder Admin hat feste Aufgabenbereiche, für die er die Entscheidungsvollmacht besitzt.\n"
                + "ii.Eine Entscheidung eines Admins kann nur durch ihn selber oder den Admin-Rat aufgehoben werden.\n"
                + "b.Admin - Rat\n"
                + "i.Der Admin-Rat setzt sich aus allen Admins zusammen und ist über die Mehrheitsentscheidung beschlussfähig.ii.Nur der Admin - Rat kann Regeländerungen abschließend genehmigen.\n"
                + "c.Operatoren\n"
                + "d. Operatoren - Rat\n"
                + "e.Eine Ausnahme für Regeln kann nur durch einen schriftlichen Antrag an die jeweiligen Räte gewährt werden. Mündliche Zusagen oder Zusagen im Chat sind nicht gültig. („Ja aber der hat gesagt“)\n"
                + "f.Überredungsversuche oder öffentliches Stimmungsmachen wird keine Entscheidungsänderung bewirken!");
        meta.addPage("8. Strafen\n\n"
                + "a. Die Admins und Operatoren behalten sich vor, Strafen ohne Verwarnung auszusprechen. Soweit möglich und angebracht, wird eine(1) Verwarnungen ausgesprochen.\n"
                + "b.Unter anderem mögliche Strafen:\n"
                + "i. Zeitweiser / Permanenter Ausschluss von Tekkit - Nerds\n"
                + "ii. Zeitweiser / Permanenter Ausschluss von einzelnen Diensten (z.B.TS, Forum, ...)\n"
                + "iii. Zurücksetzen des Accounts auf Startwerte.\n"
                + "iv.Entziehen von Grundstücken \n"
                + "v. Abreisen von Gebäuden und Anlagen\n"
                + "vi. Ingame - Coins oder Material-Strafen\n"
                + "c. Strafen können auch gegen Gruppen verhängt werden.\n"
                + "d. Strafen können auf der Schwarzen - Liste(Pranger) öffentlich notiert werden.\n"
                + "e. Wiederholtes verstoßen gegen Regeln kann zu härteren Strafen führen.\n"
                + "f. Betrug, versuchter Betrug, versuchte Regelverstöße und ähnliches werden wie richtige Verstöße gewertet.");
        meta.addPage("9. Anderes\n\n"
                + "a. Cheating und Bug - Using ist verboten. Entdeckte Sicherheitslücken sind umgehend einem Admin zu melden. Gegenüber anderer Spieler dürfen Bugs nicht veröffentlicht oder erwähnt werden, solange diese noch bestehen!\n"
                + "b. Jeder ist für seinen Account alleine verantwortlich.\n"
                + "c. Tekkit - Nerds ist nicht für Gehackte-Minecraft - Accounts zuständig. Bei Vermutungen die Hacks betreffen, hat man sich selbständig an den Hersteller zu wenden.\n"
                + "d. Verbote innerhalb dieser Regeln oder Verbote in Regelungen für einzelne Gebiete(siehe Forum) gelten immer umfassend.\n"
                + "e. Geld regiert die Welt. Bei Tekkit-Nerds machen wir aber keine Unterschiede zwischen Spielern die das Projekt Finanziell unterstützen und Spielern die hier einfach so spielen.\n"
                + "f. Sollte ein Sonderfall entstehen, welcher nicht durch die Regeln oder andere Gebote geregelt ist, gilt der -Es ist Verboten-Gedanke. Solche Situationen bitte umgehend im Forum melden.\n"
                + "g. In allen Gebieten sollte auf eine schöne Landschaft geachtet werden. Spielerfallen sind verboten.\n"
                + "h. Im Freebuild sind keine geschützten Gebiete.");
        meta.addPage("10. Gültigkeit der Regeln\n\n"
                + "a. Diese Regeln sind für alle Dienste von Tekkit-Nerds gültig.\n"
                + "b. Regeländerungen werden normalerweise 2 Wochen vor Inkrafttreten angekündigt. Ausnahmen sind dringende Änderungen.\n"
                + "c. Jeder Spieler hat sich selber über Änderungen in den Regeln zu informieren. Bei gravierenden Änderungen halten wir uns vor, neue Bestätigungen zu fordern.\n"
                + "d. Regeln im Unterforum „Regelergänzungen“ sind gültig.\n"
                + "e. Alle Regeländerungen und Ergänzungen müssen durch einen 2. Admin bestätigt werden. Ausnahme sind die Regeln auf der Homepage.\n");
        meta.addPage("11. Anträge\n\n"
                + "a. Müssen über das Formular bzw.Forum gestellt werden. Alternativ kann auch die über dem Formular angegebene E - Mail - Adresse verwendet werden.\n"
                + "b. Vergleiche mit anderen Anträgen sind zu unterlassen.\n"
                + "c. Anträge werden, soweit kein Interesse auf einstweilige Geheimhaltung besteht, mit dem Beschluss veröffentlicht.");

        buch.setItemMeta(meta);
        p.getInventory().addItem(buch);
        p.sendMessage("[TN-CC] Du hast ein Regelbuch erhalten");
        return true;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String label, String[] args) {
        return GiveRegelbuch((Player)cs);
    }
}
