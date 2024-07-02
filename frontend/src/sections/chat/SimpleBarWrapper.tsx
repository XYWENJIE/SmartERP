import './SimpleBarWrapper.css'

interface SimpleBarWrapperProps {
  children: React.ReactNode
}

const SimpleBarWrapper:React.FC<SimpleBarWrapperProps> = ({children}) => {
  return (
    <div className="simplebar-wrapper" style={{
      margin: '0px 0px -8px'
    }}>
      <div className="simplebar-height-auto-observer-wrapper">
        <div className="simplebar-height-auto-observer"></div>
      </div>
      <div className="simplebar-mask">
        <div className="simplebar-offset">
          <div className="simplebar-content-wrapper">
            <div className="simplebar-content">
              <nav>
                {children}
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default SimpleBarWrapper;