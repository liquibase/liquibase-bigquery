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
