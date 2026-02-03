# SLI / SLO Definition — TrustScore API

## 1. Service Overview

### Who is the user?
- Another application (Frontend, Backend, etc.)

### Main function of the API
- Calculate or return a trust score

### Criticality
- Medium

---
## User Expectations

The main questions users have about the application are:

1. Can I use it when I need it?
2. Is the response time fast enough?
3. When it responds, does it respond correctly?

---
## SLI Definitions

### SLI 1 — Availability

**Formula:**
```
SLI_availability = 
	(requests with HTTP status 2xx or 3xx) / total number of requests
```

**Notes:**
- 4xx responses usually do **not** count as service errors
- 5xx responses are considered service errors

---
### SLI 2 — Latency

**Formula:**
```
SLI_latency = percentage of requests with latency ≤ X ms
```

**Metric used:**
- p95 latency (95th percentile)

---
## SLO Definitions

### SLO 1 — Availability

**Proposal:**
- 99.5% monthly availability

**Note:**
- This allows approximately **3 hours and 36 minutes of downtime per month**

**Formal definition:**
```
SLO_availability:
- Time window: 30 days
- Objective: 99.5%
- Error condition: HTTP 5xx responses
```

---
### SLO 2 — Latency

**Formal definition:**
```
SLO_latency:
- 95% of requests must respond within 500ms
- Time window: 30 days
```

**Notes:**
- Test both 500ms and 300ms thresholds to observe system behavior
- This SLO can be expressed as: `p95 ≤ 500ms`

---
## Error Budget Definition

### Availability

**Formula:**
```
Error Budget = 100% - SLO
Error Budget = 100% - 99.5% = 0.5%
```

**Notes:**
- 30 days ≈ 43,200 minutes
- 0.5% corresponds to approximately **216 minutes of allowed downtime**

---
### Latency

If the latency SLO is **95% ≤ 500ms**, then:
- **5% of requests are allowed to exceed the latency threshold**
- This budget enables:
    - Deployments
    - Testing
    - Continuous system evolution