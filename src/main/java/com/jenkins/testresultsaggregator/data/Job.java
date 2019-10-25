package com.jenkins.testresultsaggregator.data;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.google.common.base.Strings;
import com.jenkins.testresultsaggregator.helper.Colors;

import hudson.model.AbstractDescribableImpl;

public class Job extends AbstractDescribableImpl<Job> implements Serializable {
	
	private static final long serialVersionUID = 34911974223666L;
	
	private String jobName;
	private String jobFriendlyName;
	//
	private JobInfo jobInfo;
	private BuildInfo buildInfo;
	private Results results;
	private ReportJob report;
	private String savedUrl;
	
	@DataBoundConstructor
	public Job(String jobName, String jobFriendlyName) {
		setJobName(jobName);
		setJobFriendlyName(jobFriendlyName);
	}
	
	public String getJobName() {
		if (jobName != null) {
			return jobName.trim();
		}
		return jobName;
	}
	
	@DataBoundSetter
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobFriendlyName() {
		if (jobFriendlyName != null) {
			return jobFriendlyName.trim();
		}
		return jobFriendlyName;
	}
	
	@DataBoundSetter
	public void setJobFriendlyName(String jonFriendlyName) {
		this.jobFriendlyName = jonFriendlyName;
	}
	
	public JobInfo getJobInfo() {
		return jobInfo;
	}
	
	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}
	
	public BuildInfo getBuildInfo() {
		return buildInfo;
	}
	
	public void setBuildInfo(BuildInfo buildInfo) {
		this.buildInfo = buildInfo;
	}
	
	public Results getResults() {
		return results;
	}
	
	public void setResults(Results results) {
		this.results = results;
	}
	
	public String getJobNameFromFriendlyName() {
		if (jobFriendlyName == null || jobFriendlyName.isEmpty()) {
			return jobName;
		}
		return jobFriendlyName;
	}
	
	public String getJobNameFromFriendlyName(boolean withLinktoResults) {
		if (withLinktoResults) {
			String reportUrl = null;
			if (results == null) {
				reportUrl = null;
			} else if (Strings.isNullOrEmpty(results.getUrl())) {
				reportUrl = null;
			} else if (JobStatus.DISABLED.name().equalsIgnoreCase(results.getCurrentResult())) {
				reportUrl = results.getUrl();
			} else {
				// reportUrl = results.getReportUrl();
				// Link for Job name redirect to Job or results ?
				reportUrl = results.getUrl();
			}
			return "<a href='" + reportUrl + "' style='text-decoration:none;'><font color='" + Colors.htmlJOB_NAME_URL() + "'>" + getJobNameFromFriendlyName() + "</font></a>";
		}
		return getJobNameFromFriendlyName();
	}
	
	public ReportJob getReport() {
		return report;
	}
	
	public void setReport(ReportJob report) {
		this.report = report;
	}
	
	public String getSavedJobUrl() {
		return savedUrl;
	}
	
	public void setSavedJobUrl(String savedUrl) {
		this.savedUrl = savedUrl;
	}
}
