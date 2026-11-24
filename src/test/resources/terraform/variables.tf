variable "project_id" {
  description = "GCP Project id"
  default     = "testharnesstests"
  type        = string
}

variable "deployer_sa" {
  description = "Deployer service account"
  default     = "gh-bq-deployer"
  type        = string
}

variable "gcp_credentials_file" {
  description = "Path of the GCP credentials file; null uses the runner's GOOGLE_CREDENTIALS. Spacelift sets it to its mounted gcp.json."
  default     = null
  nullable    = true
  type        = string
}
