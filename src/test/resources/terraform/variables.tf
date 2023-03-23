variable "project_id" {
  description = "GCP Project id"
  type        = string
}

variable "deployer_sa" {
  description = "Deployer service account"
  type        = string
}

variable "env" {
  description = "Test level"
  type        = string
}
