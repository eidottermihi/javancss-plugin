package hudson.plugins.javancss;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.plugins.javancss.parser.Statistic;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCode;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCodeDescriptor;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCodeProvider;

/**
 * @author Michael Prankl
 */
public class JavaNCSSLinesOfCodeProvider extends LinesOfCodeProvider {
    @Override
    public LinesOfCode getLOC(AbstractBuild<?, ?> build) {
        JavaNCSSProjectIndividualReport report = build.getAction(JavaNCSSProjectIndividualReport.class);
        if (report != null) {
            Statistic totals = report.getTotals();
            if (totals != null) {
                LinesOfCode loc = new LinesOfCode(totals.getNcss(), totals.getClasses());
                return loc;
            }
        }
        return null;
    }

    @Override
    public String getOrigin() {
        return PluginImpl.URL;
    }

    @Override
    public LinesOfCodeDescriptor getDescriptor() {
        return super.getDescriptor();
    }

    @Extension
    public static class DescriptorImpl extends LinesOfCodeDescriptor {

        @Override
        public String getDisplayName() {
            return PluginImpl.DISPLAY_NAME;
        }

    }
}
