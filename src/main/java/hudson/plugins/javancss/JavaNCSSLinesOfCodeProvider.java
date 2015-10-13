package hudson.plugins.javancss;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.plugins.javancss.parser.Statistic;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCode;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCodeDescriptor;
import org.jenkinsci.plugins.codehealth.provider.loc.LinesOfCodeProvider;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Michael Prankl
 */
public class JavaNCSSLinesOfCodeProvider extends LinesOfCodeProvider {

    @DataBoundConstructor
    public JavaNCSSLinesOfCodeProvider() {

    }

    @Override
    public LinesOfCode getLOC(AbstractBuild<?, ?> build) {
        JavaNCSSBuildIndividualReport report = build.getAction(JavaNCSSBuildIndividualReport.class);
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

    public String getBuildResultUrl() {
        return PluginImpl.URL;
    }

    public String getProjectResultUrl() {
        return PluginImpl.URL;
    }

    @Extension
    public static class DescriptorImpl extends LinesOfCodeDescriptor {

        @Override
        public String getDisplayName() {
            return PluginImpl.DISPLAY_NAME;
        }

    }
}
