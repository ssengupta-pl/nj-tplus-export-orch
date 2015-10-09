/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

/**
 * @author ssengupta
 */
public class ExportJob {
	private String user;

	private String requestedResource;

	private String requestedOperation;

	private String subscriptionLevel;

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the requestedResource
	 */
	public String getRequestedResource() {
		return requestedResource;
	}

	/**
	 * @param requestedResource
	 *            the requestedResource to set
	 */
	public void setRequestedResource(String requestedResource) {
		this.requestedResource = requestedResource;
	}

	/**
	 * @return the requestedOperation
	 */
	public String getRequestedOperation() {
		return requestedOperation;
	}

	/**
	 * @param requestedOperation
	 *            the requestedOperation to set
	 */
	public void setRequestedOperation(String requestedOperation) {
		this.requestedOperation = requestedOperation;
	}

	/**
	 * @return the subscriptionLevel
	 */
	public String getSubscriptionLevel() {
		return subscriptionLevel;
	}

	/**
	 * @param subscriptionLevel
	 *            the subscriptionLevel to set
	 */
	public void setSubscriptionLevel(String subscriptionLevel) {
		this.subscriptionLevel = subscriptionLevel;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (requestedOperation == null ? 0
				: requestedOperation.hashCode());
		result = prime * result + (requestedResource == null ? 0
				: requestedResource.hashCode());
		result = prime * result + (subscriptionLevel == null ? 0
				: subscriptionLevel.hashCode());
		result = prime * result + (user == null ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ExportJob)) {
			return false;
		}
		ExportJob other = (ExportJob) obj;
		if (requestedOperation == null) {
			if (other.requestedOperation != null) {
				return false;
			}
		} else if (!requestedOperation.equals(other.requestedOperation)) {
			return false;
		}
		if (requestedResource == null) {
			if (other.requestedResource != null) {
				return false;
			}
		} else if (!requestedResource.equals(other.requestedResource)) {
			return false;
		}
		if (subscriptionLevel == null) {
			if (other.subscriptionLevel != null) {
				return false;
			}
		} else if (!subscriptionLevel.equals(other.subscriptionLevel)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExportJob [user=");
		builder.append(user);
		builder.append(", requestedResource=");
		builder.append(requestedResource);
		builder.append(", requestedOperation=");
		builder.append(requestedOperation);
		builder.append(", subscriptionLevel=");
		builder.append(subscriptionLevel);
		builder.append("]");
		return builder.toString();
	}
}
